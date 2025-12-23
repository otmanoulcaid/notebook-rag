all : up

up :
	@docker compose $@ -d

build :
	@docker compose up -d --build

stop :
	@docker compose $@

app : front back

front :
	@cd client/app && npm i && ng serve

back :
	@cd rag/app && mvn clean && mvn spring-boot:run -Ddev.langchain4j.http.client.factory=spring

clean :
	@echo "cleaning..."
	@docker container prune -f
	@docker network prune -f

fclean: clean
	@docker image prune -f -a
	@docker volume prune -f -a


re : clean up

rebuild : clean build