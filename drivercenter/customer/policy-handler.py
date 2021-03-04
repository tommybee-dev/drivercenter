#-*- coding: euc-kr -*-

from flask import Flask
from redis import Redis, RedisError
from kafka import KafkaConsumer
import os
import socket

KAFKA_HOSTS = ['my-kafka.kafka.svc.cluster.local:9092']
KAFKA_VERSION = (0, 10)

# To consume latest messages and auto-commit offsets
consumer = KafkaConsumer('drivercenter',
                         group_id='customer',
						 bootstrap_servers=KAFKA_HOSTS)
#                         bootstrap_servers=KAFKA_HOSTS, api_version=KAFKA_VERSION)
for message in consumer:
  print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition, message.offset, message.key, message.value.decode('utf-8')))


