import React, { useState } from 'react';
import { Typography, Table, Modal, Input, Form, Button, message, Spin } from 'antd';
import promiseAjax from '@/utils/promiseAjax';
import { useDidMount, useWillUnmount, useForceUpdate } from 'zero-element/lib/utils/hooks/lifeCycle';
import { get as getEndpoint } from 'zero-element/lib/utils/request/endpoint';

const { Title } = Typography;

const layout = {
  labelCol: { span: 5 },
  wrapperCol: { span: 19 },
};

const tailLayout = {
  wrapperCol: { offset: 14, span: 10 },
};

export default function (props) {

  const { title = '权限定义列表' } = props;

  const [form] = Form.useForm();

  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const [permList, setPermList] = useState([]);
  const [currentRecord, setCurrentRecord] = useState({});

  useDidMount(_ => {
      getListAction()
  });

  useWillUnmount(_ => {
    setPermList([])
    setShowModal(false)
    setCurrentRecord({})
  });

  function getListAction() {
    setLoading(true)
    const apiUrl = `${getEndpoint()}/api/adm/perm/allPermList`;
    const queryData = {}
    promiseAjax(apiUrl, queryData)
    .then(resp => {
      // console.log(resp , '获取权限信息')
      setLoading(false)
      if (resp.code===200) {
        const data = resp.data;
        if(data && data.length > 0){
          data.map((item, index) => {
            item.key = index+1
            return item
          })
          setPermList(data)
        }
      } else {
        console.error('获取权限信息失败')
      }
    })
  }

  //添加权限
  function postAction(addData) {
    setLoading(true)
    const apiUrl = `${getEndpoint()}/api/adm/perm/perms`;
    const queryData = {...addData}
    console.log('queryData === ', queryData)
    promiseAjax( apiUrl, queryData, { method:'POST'})
    .then(resp => {
      // console.log(resp , '升级精灵操作')
      setLoading(false)
      if (resp.code===200) {
        // const data = resp.data;
        setShowModal(false)
        message.success('提交成功')
        getListAction()
      } else {
        message.success('提交失败')
        console.error(resp, ' 提交失败 ')
      }
    })
  }

  function showModalAction(record) {
    setShowModal(true)
    setCurrentRecord(record)
  }

  function handleCancel() {
    onReset()
    setShowModal(false)
  }

  function onFinish(e) {
    // console.log(e, ' 执行 ')
    const data = e
    e.identifier = currentRecord.permission
    postAction(data)
  }

  const onReset = () => {
    form.resetFields();
  };


  const columns = [
    {
      dataIndex: 'key', title: '编号',
      render: (text, record) => text || record.key
    },
    {
      dataIndex: 'permName', title: '权限名',
      render: (text, record) => text || record.permName
    },
    {
      dataIndex: 'permission', title: '识别字符',
      render: (text, record) => text || record.permission
    },
    {
      dataIndex: 'operation', title: '操作',
      render: (text, record, index) => {
        if(record.hasPermission === 0){
          return <a href="#" onClick={() =>showModalAction(record)}>添加</a>
        }
      }
    },
  ];

  return (
    <div>
      <Title level={4}>{title}</Title>
      <Spin spinning={loading}>
      <Table
        rowKey="key"
        bordered
        columns={columns}
        dataSource={permList}
        pagination={false}
      />
      </Spin>
      <Modal title="添加权限" visible={showModal} footer={null} onCancel={handleCancel} >
        <Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>

          <Form.Item name="name" label="权限名称" rules={[{ required: true }]}>
            <Input />
          </Form.Item>

          <Form.Item name="identifier" label="识别字符" rules={[]} >
            <Input defaultValue={currentRecord.permission} disabled/>
          </Form.Item>
          <Form.Item {...tailLayout} style={{marginTop: '70px'}}>
            <Button htmlType="button" onClick={onReset} style={{marginRight: '10px'}}>
              重置
            </Button>
            <Button type="primary" htmlType="submit" loading={loading}>
              提交
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  )

}