#!/bin/bash
#------------------------------------------------------

ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook playbook.yml --private-key=~/.ssh/id_rsb.pub -u helenapoleri -v