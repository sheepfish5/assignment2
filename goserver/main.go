package main

import (
	"fmt"
	"os"

	"sheepfish5.com/goserver/db_access"
	"sheepfish5.com/goserver/protos/stuservice"
	"sheepfish5.com/goserver/util"
)

func StuToString(stu *stuservice.Student) string {
	return fmt.Sprintf("id=%d, name=%s, gender=%s, birthday=%s", 
			stu.Id, stu.Name, stu.Gender, util.GetDateString(stu.Birthday))
}

func main() {
	var dba db_access.DBAccess
	dba.InitDB(
		os.Getenv("DBUSER"),
		os.Getenv("DBPASS"),
		os.Getenv("DBNAME"),
	)

	stu := dba.QueryByID(1)
	stus := dba.QueryByName("avi")

	fmt.Println(StuToString(stu))
	for _, stu := range stus {
		fmt.Println(StuToString(stu))
	}

	// date := "2001-01-01"
	// birthday, _ := util.GetBirthDayTimeStamp(&date)
	// newStu := stuservice.Student{
	// 	Name: "Hello World",
	// 	Gender: "female",
	// 	Birthday: birthday,
	// }
	// insertId, _ := dba.AddStudent(&newStu)
	// newStu.Id = insertId
	// fmt.Printf("insert: %s\n", StuToString(&newStu))
	rows := dba.DeleteByID(11)
	fmt.Printf("delete %d row(s)\n", rows)
}