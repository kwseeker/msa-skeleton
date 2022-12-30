package server

import (
	"context"
	"log"
	"net"
	"net/http"
	"time"
)

const (
	bindAddr    = ":8080"
	timeout     = 5 * time.Second
	idleTimeout = 30 * time.Second
)

func init() {
	RegisterRoute()
}

type Server struct {
	*http.Server
}

func DefaultNew() *Server {
	return New(nil, bindAddr, timeout, idleTimeout)
}

func New(handler http.Handler, bindAddr string, timeout, idleTimeout time.Duration) *Server {
	srv := &Server{
		Server: &http.Server{
			Addr:              bindAddr,
			Handler:           handler,
			ReadTimeout:       timeout,
			ReadHeaderTimeout: timeout,
			WriteTimeout:      timeout,
			IdleTimeout:       idleTimeout,
		},
	}
	return srv
}

func (s *Server) Start(ctx context.Context) error {
	log.Println("start http server...")
	s.BaseContext = func(listener net.Listener) context.Context {
		return ctx
	}
	return s.ListenAndServe()
}

func (s *Server) Stop(ctx context.Context) error {
	log.Println("stop http server...")
	return s.Shutdown(ctx)
}
