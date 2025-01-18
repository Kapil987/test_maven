FROM docker:dind

# Install Git and Maven
RUN apk update && \
    apk add git && \
    apk add maven

# Set a shell as the default command
CMD ["sh"]