# syntax=docker/dockerfile:1

FROM golang:1.22-alpine AS build
WORKDIR /src
RUN --mount=type=bind,source=go.mod,target=go.mod \
    --mount=type=bind,source=go.sum,target=go.sum \
    go mod download
RUN --mount=type=bind,source=.,target=. \
    go build -o /bin/goserver .

FROM scratch
COPY --from=build /bin/goserver /bin/
ENTRYPOINT [ "/bin/goserver" ]
EXPOSE 18899