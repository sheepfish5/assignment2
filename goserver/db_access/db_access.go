package db_access

import (
	"database/sql"
	"errors"
	"fmt"
	"log"

	"github.com/go-sql-driver/mysql"
	"sheepfish5.com/goserver/protos/stuservice"
	"sheepfish5.com/goserver/util"
)

type DBAccess struct {
	db *sql.DB
}

var ErrDBNIL = errors.New("db == nil")

/* int this assignment, these arguments are solid */
func (dba *DBAccess) InitDB(user, password, dbName string) {
	log.Println("init mysql access...")

	cfg := mysql.Config{
		User: user,
		Passwd: password,
		Net:    "tcp",
        Addr:   "127.0.0.1:3306",
        DBName: dbName,
	}

	var err error
	dba.db, err = sql.Open("mysql", cfg.FormatDSN())
	if err != nil {
		log.Println("failed to open a database handler.")
		log.Fatal(err)
	}

	pingErr := dba.db.Ping()
	if pingErr != nil {
		log.Println("failed to connect to mysql.")
		log.Fatal(pingErr)
	}
	log.Println("success to access mysql.")
}

/* return the id of the student passed in */
func (dba *DBAccess) AddStudent(stu *stuservice.Student) (int64, error) {
	if dba.db == nil {
		return 0, ErrDBNIL
	}
	log.Printf("AddStudent: insert: name=%s, gender=%s, birthday=%s", stu.Name, stu.Gender, stu.Birthday)
	result, err := dba.db.Exec("insert into students (name, gender, birthday) value (?, ?, ?);", 
			stu.Name, stu.Gender, util.GetDateString(stu.Birthday))
	if err != nil {
		return 0, fmt.Errorf("AddStudent: %v", err)
	}
	id, err := result.LastInsertId()
	if err != nil {
		return 0, fmt.Errorf("AddStudent: %v", err)
	}
	return id, nil
}

func (dba *DBAccess) QueryByID(id int64) *stuservice.Student {
	var stu stuservice.Student
	var birthday string

	row := dba.db.QueryRow("select id, name, gender, birthday from students where id = ?;", id)
	if err := row.Scan(&stu.Id, &stu.Name, &stu.Gender, &birthday); err != nil {
		return new(stuservice.Student)		
	}
	stu.Birthday, _ = util.GetBirthDayTimeStamp(&birthday)
	return &stu
}

func (dba *DBAccess) QueryByName(name string) ([]*stuservice.Student) {
	var stus []*stuservice.Student

	rows, err := dba.db.Query("select id, name, gender, birthday from students where name like ?;", 
			fmt.Sprintf("%%%s%%", name))
	if err != nil {
		return nil
	}
	defer rows.Close()
	for rows.Next() {
		var stu stuservice.Student
		var birthday string
		if err := rows.Scan(&stu.Id, &stu.Name, &stu.Gender, &birthday); err != nil {
			return nil
		}
		stu.Birthday, _ = util.GetBirthDayTimeStamp(&birthday)
		stus = append(stus, &stu)
	}
	if err := rows.Err(); err != nil {
		return nil
	}
	return stus
}

/* return the number of rows effected */
func (dba *DBAccess) DeleteByID(id int64) int64 {
	result, err := dba.db.Exec("delete from students where id = ?;", id)
	if err != nil {
		return 0
	}
	rows, err := result.RowsAffected()
	if err != nil {
		return 0
	}
	return rows
}