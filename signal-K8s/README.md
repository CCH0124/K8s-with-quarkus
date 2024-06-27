
## K3d
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

## Kind


```bash
kind create cluster --name selflab --config infra/kind/config.yaml

kubectl cluster-info --context kind-selflab
```

## Building container

```bash
podman build --platform linux/amd64,linux/arm64 -t single-simple .
```

```bash
podman login docker.io
podman tag localhost/single-simple docker.io/cch0124/single-simple:latest
```

```bash
podman push single-simple:latest 
```