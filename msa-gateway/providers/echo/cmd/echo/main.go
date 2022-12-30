package main

import (
	"context"
	"echo/registry"
	"echo/server"
	"log"
)

func main() {
	ctx := context.Background()
	done := make(chan int, 1)

	//启动 http server
	srv := server.DefaultNew()
	go func() {
		err := srv.Start(ctx)
		if err != nil {
			log.Fatal("start http server failed: ", err)
		}
	}()

	//注册到 nacos 注册中心
	r := registry.New()
	r.Register()
	defer r.Deregister()

	log.Println("echo server started!")
	<-done
}
