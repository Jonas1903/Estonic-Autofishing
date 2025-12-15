#!/bin/sh

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

JAVA_HOME=""
JAVA_OPTS=""

if [ -z "$JAVA_HOME" ] ; then
    JAVACMD="java"
else
    JAVACMD="$JAVA_HOME/bin/java"
fi

exec "$JAVACMD" $JAVA_OPTS $DEFAULT_JVM_OPTS -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
