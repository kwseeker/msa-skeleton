package registry

import (
	"github.com/nacos-group/nacos-sdk-go/v2/clients"
	"github.com/nacos-group/nacos-sdk-go/v2/clients/naming_client"
	"github.com/nacos-group/nacos-sdk-go/v2/common/constant"
	"github.com/nacos-group/nacos-sdk-go/v2/vo"
	"log"
)

type Registrar interface {
	Register()
	Deregister()
}

type NacosRegistrar struct {
	Registrar
	cli naming_client.INamingClient
	//sc []constant.ServerConfig
	//cc constant.ClientConfig
}

func New() Registrar {
	return &NacosRegistrar{}
}

// Register 配置先写死
func (n *NacosRegistrar) Register() {
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
	n.cli = cli

	registerParam := vo.RegisterInstanceParam{
		Ip:          "127.0.0.1",
		Port:        8080,
		ServiceName: "srv-echo",
		//GroupName:   "group-a",	//DEFAULT_GROUP
		ClusterName: "SZ",
		Weight:      10,
		Enable:      true,
		Healthy:     true,
		Ephemeral:   true,
		Metadata:    map[string]string{"idc": "shenzhen"},
	}
	success, err := cli.RegisterInstance(registerParam)
	if !success || err != nil {
		log.Fatal("RegisterServiceInstance failed! ", err)
	}
	log.Printf("RegisterServiceInstance, param:%+v, result:%+v \n", registerParam, success)
}

func (n *NacosRegistrar) Deregister() {
	deregisterParam := vo.DeregisterInstanceParam{
		Ip:          "127.0.0.1",
		Port:        8080,
		ServiceName: "srv-echo",
		//GroupName:   "group-a",
		Cluster:   "SZ",
		Ephemeral: true, //it must be true
	}
	success, err := n.cli.DeregisterInstance(deregisterParam)
	if !success || err != nil {
		log.Fatal("DeRegisterServiceInstance failed!")
	}
	log.Printf("DeRegisterServiceInstance, param:%+v, result:%+v \n", deregisterParam, success)
}
