FROM tomcat:8.0

RUN mkdir webapps/client1

COPY client-1/html/ webapps/client1/

CMD ["catalina.sh", "run"]