package registry

import (
	"github.com/nacos-group/nacos-sdk-go/v2/clients"
	"github.com/nacos-group/nacos-sdk-go/v2/common/constant"
	"github.com/nacos-group/nacos-sdk-go/v2/vo"
	"log"
	"testing"
)

func TestDeregister(t *testing.T) {
	sc := []constant.ServerConfig{
		*constant.NewServerConfig("127.0.0.1", 8848, constant.WithContextPath("/nacos")),
	}
	cc := *constant.NewClientConfig(
		constant.WithNamespaceId(""),
		constant.WithTimeoutMs(5000),
		constant.WithNotLoadCacheAtStart(true),
		constant.WithLogDir("/tmp/nacos/log"),
		constant.WithCacheDir("/tmp/nacos/cache"),
		constant.WithLogLevel("debug"),
	)
	cli, err := clients.NewNamingClient(
		vo.NacosClientParam{
			ClientConfig:  &cc,
			ServerConfigs: sc,
		},
	)
	if err != nil {
		log.Fatal("register service failed: ", err)
	}

	deregisterParam := vo.DeregisterInstanceParam{
		Ip:          "127.0.0.1",
		Port:        8080,
		ServiceName: "srv-echo",
		//GroupName:   "group-a",
		Cluster:   "SZ",
		Ephemeral: false, //it must be true
	}
	success, err := cli.DeregisterInstance(deregisterParam)
	if !success || err != nil {
		log.Fatal("DeRegisterServiceInstance failed!")
	}
	log.Printf("DeRegisterServiceInstance, param:%+v, result:%+v \n", deregisterParam, success)
}
