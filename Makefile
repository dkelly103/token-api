.PHONY: clean
clean:
	./gradlew clean
	npm prune
	rm -rf node_modules

.PHONY: build
build:
	./gradlew generateContractWrapper build --stacktrace

.PHONY: build-docker
build-docker:
	docker build --platform linux/x86_64 -t token-api .

.PHONY: explore-image
explore-image: build-docker
	 docker run -it --entrypoint bash token-api