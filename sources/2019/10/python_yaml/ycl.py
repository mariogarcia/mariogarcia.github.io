import logging
import os

from addict import Dict
from yaml import load, Loader, YAMLError


log = logging.getLogger("config")


class YamlConfigLoader:
    def load(self, path):
        if path and os.path.exists(path): 
            with open(path, 'r') as ymlfile:
                try:
                    yaml = load(ymlfile, Loader=Loader) # <1>
                    conf = Dict(yaml) # <2>

                    return conf
                except YAMLError as error:
                    log.error("config/error/yaml: {}".format(error))
        else:
            log.error("config/error/not_found: {}".format(path))
