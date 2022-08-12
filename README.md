# Project Name

MyWallet - E-Wallet service

## 🔧 Functions

### Function 01: Person
- Registration
- Search by term
- Search by id

### Function 02: Wallet
- Create 
- Deposit
- Withdraw
- Transfer
- Balance
- Statement

## Dependencies 

[![Docker](https://img.shields.io/badge/Docker-0395bf?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

# Run Project

If you just want to run the project, just download the [Dockerfile](Dockerfile) and run the commands below in the folder where the Dockerfile is:

1. Build project image 

    ```
    docker build -t any_name_you_want-api --build-arg db_server=your_db_server --build-arg db_port=your_db_port --build-arg db_name=your_db_name --build-arg db_user=your_db_user --build-arg db_password=your_db_password --build-arg front_server=your_frontend_server_to_allowed_origin_in_cors .
    ``` 

2. Run docker image

    ```
	sudo docker run -it --rm -p any_port_you_want:8080 name_you_chose_in_build-t
    ``` 

## Made with:
[![UBUNTU](https://img.shields.io/badge/Ubuntu-e95420?style=for-the-badge&logo=ubuntu&logoColor=white)](https://ubuntu.com/download)
[![JAVA](https://img.shields.io/badge/Java-cc0000?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![ECLIPSE](https://img.shields.io/badge/Eclipse-2c2255?style=for-the-badge&logo=eclipse&logoColor=white)](https://www.eclipse.org/downloads/)
[![SPRING](https://img.shields.io/badge/Spring-6db33f?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![LIQUIBASE](https://img.shields.io/badge/Liquibase-ff3d00?style=for-the-badge&logo=liquibase&logoColor=white)](https://www.liquibase.org/)
[![INSOMNIA](https://img.shields.io/badge/Insomnia-6600d8?style=for-the-badge&logo=insomnia&logoColor=white)](https://insomnia.rest/)

## 🔖 License
[![LICENSE](https://img.shields.io/badge/Custom_GPL_3.0-E58080?style=for-the-badge&logo=bookstack&logoColor=white)](/LICENSE)

### Support or contact

[![GITHUB](https://img.shields.io/badge/Github-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/dmarlon/)
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/marlon-dauernheimer-55278073/)

### Reference Documentation
To additional references, consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Liquibase](https://docs.liquibase.com/home.html)

