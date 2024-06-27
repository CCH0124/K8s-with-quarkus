```bash
podman machine init k3dlab --memory=4096 k3dlab
podman system connection default k3dlab 
podman network create self-lab
```

creat K8s cluster using podman

```bash
host_gateway=$(podman network inspect -f "{{range .Subnets}}{{.Gateway}}{{end}}" self-lab 2>/dev/null || echo "host-gateway")
k3d cluster create selflab --config infra/k3d/config.yaml --agents-memory=3G  --servers-memory=2G
```