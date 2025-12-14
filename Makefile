all : up

up :
	@docker compose $@ -d --build

stop :
	@docker compose $@

app : tmp-front tmp-back

tmp-front :
	@cd client/app && npm i && ng serve

tmp-back :
	@cd rag/app && mvn spring-boot:run

clean :
	@echo "cleaning..."
	@docker container prune -f
	@docker network prune -f

fclean: clean
	@docker image prune -f -a
	@docker volume prune -f -a
