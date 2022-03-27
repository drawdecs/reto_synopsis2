#!/bin/bash
PATH_LOCAL=$(pwd)

cd $PATH_LOCAL/server-config
PATH_SERVER_CONFIG=$(pwd)
#cd $PATH_LOCAL/server-discovery
#PATH_SERVER_DISCOVERY=$(pwd)
cd $PATH_LOCAL/transpselva-access
PATH_TRANSP_SELVA=$(pwd)
#cd $PATH_LOCAL/artifacts
#PATH_ARTIFACTS=$(pwd)

echo "PATH PATH_SERVER_CONFIG: ${PATH_SERVER_CONFIG}"
echo "PATH PATH_TRANSP_SELVA: ${PATH_TRANSP_SELVA}"

compileProject() 
{
    arr=("$@")
    directory=${arr[0]}
    cd "${directory}"
    echo "Compilando $(pwd)...."
    ./gradlew clean bootJar
    cp "build/libs/${arr[1]}" "${PATH_ARTIFACTS}/${arr[2]}"
}

compileProject "${PATH_SERVER_CONFIG}" "server-config-0.0.1.jar" "server-config.jar"
compileProject "${PATH_TRANSP_SELVA}" "transpselva-access-0.0.1.jar" "transpselva-access.jar"
