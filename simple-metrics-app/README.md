# Simple metrics app

This is very simple application producing some metrics for the sake of tests and learning.

## Deploying to kubernetes

Just run ansible playbook for deployment:

```
ansible-playbook -i ../ansible/hosts.ini ansible-playbooks/deploy.yaml
```