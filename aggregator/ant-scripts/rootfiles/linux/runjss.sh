#!/bin/bash
DIR=$(dirname "$0")
export UBUNTU_MENUPROXY=0;
export SWT_GTK3=0;
"$DIR"/Jaspersoft\ Studio $*
