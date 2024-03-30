package util

import (
	"time"

	"google.golang.org/protobuf/types/known/timestamppb"
)

const BirthdayLayout = "2006-01-02"

func GetDateString(t *timestamppb.Timestamp) string {
	return t.AsTime().Format(BirthdayLayout)
}

func GetBirthDayTimeStamp(date *string) (*timestamppb.Timestamp, error) {
	t, err := time.ParseInLocation(BirthdayLayout, *date, time.Local)
	if err != nil {
		return nil, err
	}
	return timestamppb.New(t), nil
}