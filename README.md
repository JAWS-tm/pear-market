# Pear Market
This project is an simple e-shop built using JakartaEE. The project can be launched using Docker.

## Install
> Docker must be installed on your computer

- Clone the project

`git clone git@github.com:JAWS-tm/pear-market.git`

- Configure environment variables in the .env file
```
DB_PORT=3306
DB_USER=user
DB_PASSWORD=password
DB_NAME=projet_shop # Must be the same as in init.sql

APP_PORT=8080
```

- Launch Docker compose

`docker compose up -d`

## Troubleshooting

When you change something in the app, you should force rebuild the Docker image when you run compose :

`docker compose up -d --build`

