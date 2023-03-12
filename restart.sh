#!/bin/bash

source env_local.sh

#create share network
docker network inspect $SANDBOX_PROJECT >/dev/null 2>&1 || \
    docker network create --driver bridge $SANDBOX_PROJECT

PARAMETERS=( "$@" )

[ "XX$PARAMETERS" = "XX" ] && PARAMETERS="sandbox";
[ "$PARAMETERS" = "all" ] && PARAMETERS=(
kafka
mongodb
sandbox
);

for var in "${PARAMETERS[@]}"
do
    case "$var" in
    kafka)
        docker compose -p $KAFKA_PROJECT -f $KAFKA_COMPOSE_FILE down
        docker compose -p $KAFKA_PROJECT -f $KAFKA_COMPOSE_FILE up --build --no-color --detach
    ;;
    mongodb)
        docker compose -p $MONGODB_PROJECT -f $MONGODB_COMPOSE_FILE down
        docker compose -p $MONGODB_PROJECT -f $MONGODB_COMPOSE_FILE up --build --no-color --detach
    ;;
    sandbox)
        docker compose -p $SANDBOX_PROJECT -f $SANDBOX_COMPOSE_FILE down
        docker compose -p $SANDBOX_PROJECT -f $SANDBOX_COMPOSE_FILE up --build --no-color --detach
    ;;
    esac
done

docker image prune -f
