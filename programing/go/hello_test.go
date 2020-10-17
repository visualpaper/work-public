package main

import (
	"testing"
)

func TestMain(t *testing.T) {
	expect := "aaaaa"
	actual := Sample()

	if expect != actual {
		t.Errorf("%s != %s", expect, actual)
	}
}
