all : up

up :
	@docker compose $@ -d --build

app : tmp-front-rule tmp-back-rule

tmp-front-rule :
	@cd client/app && npm i && ng serve

tmp-back-rule :
	@cd rag/app && mvn spring-boot:run

clean :
	@echo "cleaning..."
	@docker container prune -f -a
	@docker image prune -f -a
	@docker network prune -f -a

fclean: clean
	@docker volume prune -f -a
