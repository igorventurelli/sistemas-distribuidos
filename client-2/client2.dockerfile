FROM golang:1.8 
RUN mkdir /app 
ADD client-2/go/ /app/ 
WORKDIR /app 
ENV PATH $PATH:$HOME/app
#RUN go build -o main . 
CMD /usr/local/bin/shell.sh ; sleep infinity