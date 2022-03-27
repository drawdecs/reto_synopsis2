#!/bin/bash
PATH_ARTIFACTORY=../artifacts

PATH_LOCAL=$(pwd)

cd ${PATH_LOCAL}
cd ${PATH_ARTIFACTORY}
PATH_ARTIFACTORY=$(pwd)

./gradlew clean bootJar
cp build/libs/server-config-0.0.1.jar ${PATH_ARTIFACTORY}/server-config.jar