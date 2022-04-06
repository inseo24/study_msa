test 할 때 서버 띄우고 localhost:8888/first-service/local <- 이런 식으로 검색해볼 수 있음

아래 보며 spring cloud config에서 EnvironmentController를 제공하고 있어서 모두 검색가능

검색 방식


{config-server ip}/{service name}/{profiles}


https://github.com/spring-cloud/spring-cloud-config/blob/main/spring-cloud-config-server/src/main/java/org/springframework/cloud/config/server/environment/EnvironmentController.java
