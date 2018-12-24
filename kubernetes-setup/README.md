# Instructions

##Running locally

Use this command to work with internal kubernetes registry

```
kubectl create -f interal-registry.yaml
kubectl port-forward --namespace kube-system $(kubectl get po -n kube-system | grep kube-registry-v0 | awk '{print $1;}') 5000:5000 &
docker push localhost:5000/image:tag
```