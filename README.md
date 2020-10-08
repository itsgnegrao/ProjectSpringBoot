# ProjectSpringBoot

Projeto Spring Boot Java + Dockerfile

## Pre-requisitos

Assumo que as seguintes dependências estão instaladas e funcionando:

- [Docker 19.03.13](http://www.docker.io/gettingstarted/#h_installation)

## Build + Exec

### Passos para construção da imagem Docker:

##### (Opção 1 - Automated Builds from github push enable!) Docker Hub:

1.  Clone a imagem previamente contruída:

        docker pull itsgnegrao/project-spring-boot

    Este comando deve levar algum tempo, tome uma xícara de café ...

##### (Opção 2) Github:

1.  Clone o repositório:

        git clone https://github.com/itsgnegrao/ProjectSpringBoot.git

2.  Construa a imagem:

        cd ProjectSpringBoot
        docker build -t itsgnegrao/itsgnegrao-spring-boot .

    Este comando deve levar algum tempo, tome uma xícara de café ...

#### Passos para execução da imagem Docker:

1.  Execute a imagem:

        docker run -it -p 8080:8080 itsgnegrao/itsgnegrao-spring-boot:latest

2.  Acesse o endereço da API para teste:

        http://localhost:8080/source
