all : up

up :
	@docker compose --build -d $@

app : tmp-front-rule tmp-back-rule

tmp-front-rule :
	@cd client && npm i && ng serve

tmp-back-rule :
	@cd rag && mvn spring-boot:run

clean :
	@echo "cleaning..."
	@docker container prune -f -a
	@docker image prune -f -a
	@docker network prune -f -a

fclean: clean
	@docker volume prune -f -a
