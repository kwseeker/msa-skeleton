package server

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	"time"
)

func helloHandler(w http.ResponseWriter, req *http.Request) {
	log.Println(req.RemoteAddr, "inside root handler, request detail: ")
	// 请求方式：GET POST DELETE PUT UPDATE
	log.Println("method:", req.Method)
	log.Println("url:", req.URL.Path)
	log.Println("header:", req.Header)
	log.Println("body:", req.Body)

	_, err := w.Write([]byte("Hello " + req.URL.Path[len("/hello/"):]))
	if err != nil {
		log.Println(err)
	}
}

/*
模拟一个响应很慢的请求处理
*/
func slowRespHandler(w http.ResponseWriter, req *http.Request) {
	log.Println(req.RemoteAddr, "inside root handler, request detail: ")
	// 请求方式：GET POST DELETE PUT UPDATE
	log.Println("method:", req.Method)
	log.Println("url:", req.URL.Path)
	log.Println("header:", req.Header)
	log.Println("body:", req.Body)

	time.Sleep(5 * time.Second)

	_, err := w.Write([]byte("Slow Response"))
	if err != nil {
		log.Println(err)
	}
}

type TestStruct struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

// json 请求处理
func jsonHandler(w http.ResponseWriter, req *http.Request) {
	log.Println(req.RemoteAddr, "inside root handler, request detail: ")
	// 请求方式：GET POST DELETE PUT UPDATE
	log.Println("method:", req.Method)
	log.Println("url:", req.URL.Path)
	log.Println("header:", req.Header)
	log.Println("body:", req.Body)

	body, err := ioutil.ReadAll(req.Body)
	if err != nil {
		panic(err)
	}
	log.Println(string(body))

	var t TestStruct
	err = json.Unmarshal(body, &t)
	//decoder := json.NewDecoder(req.Body)
	//err = decoder.Decode(&t)
	if err != nil {
		panic(err)
	}
	log.Println(t)

	//再原封不动写回去
	_, respErr := w.Write(body)
	if respErr != nil {
		log.Println(respErr)
	}
}

func RegisterRoute() {
	http.HandleFunc("/hello/", helloHandler)
	http.HandleFunc("/slow/", slowRespHandler)
	http.HandleFunc("/json/", jsonHandler)
}
