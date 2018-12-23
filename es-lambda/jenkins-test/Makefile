
# define
DIST_DIRECTORY_PATH = ./dist
WORK_DIRECTORY_PATH = ./workspace
ZIP_FILE_NAME_PREFIX = es-delete-lambda

# default run task
.PHONY: all

all: clean init copy build

clean:
	if [ -e $(WORK_DIRECTORY_PATH) ] ; then rm -r $(WORK_DIRECTORY_PATH) ; fi
	if [ -e $(DIST_DIRECTORY_PATH) ] ; then rm -r $(DIST_DIRECTORY_PATH) ; fi

init:
	mkdir $(WORK_DIRECTORY_PATH)

copy:
	cp -p ./*.py $(WORK_DIRECTORY_PATH)

build:
	# compile
	python -m compileall $(WORK_DIRECTORY_PATH)
	rm $(WORK_DIRECTORY_PATH)/*.py

	# install requirements
	pip install -r requirements.txt -t $(WORK_DIRECTORY_PATH)

	# create zip
	cd ./$(WORK_DIRECTORY_PATH) ; zip -r $(ZIP_FILE_NAME_PREFIX)-$(VERSION)-$(TIMESTAMP_YYMMDD).zip *

install:
	mkdir $(DIST_DIRECTORY_PATH)
	mv ./$(WORK_DIRECTORY_PATH)/$(ZIP_FILE_NAME_PREFIX)*.zip $(DIST_DIRECTORY_PATH)/

