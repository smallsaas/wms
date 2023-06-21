import React, { useRef } from 'react';
import { Form, Input, Button, Checkbox } from 'antd';
import { UserOutlined, LockOutlined, KeyOutlined } from '@ant-design/icons';
import Flex from '@/../zero-antd-dep/layout/Flex';

import ReactSimpleVerify from 'react-simple-verify'
import 'react-simple-verify/dist/react-simple-verify.css'

import styles from '../index.less';

const { FlexItem } = Flex;

export default function AccountForm(props) {
  const { className, onRePW, onSubmit, loading } = props;
  const verifyRef = useRef();

  return <Form className={className} onFinish={onSubmit}>
    <Form.Item name="account" rules={[{ required: true, message: '请输入用户名/手机号/邮箱' }]}>
      <Input
        prefix={<UserOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
        placeholder="用户名/手机号/邮箱"
      />
    </Form.Item>
    <Form.Item name="password" rules={[{ required: true, message: '请输入密码' }]}>
      <Input
        prefix={<LockOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
        type="password"
        placeholder="密码"
      />
    </Form.Item>
    {/* <ReactSimpleVerify ref={verifyRef} width={328} /> */}
    <Flex>
      <FlexItem>
        <Form.Item name="remember" valuePropName="checked" initialValue={true}>
          <Checkbox>自动登录</Checkbox>
        </Form.Item>
      </FlexItem>
      <FlexItem>
        <Button type="link" className={styles.forgot}
          onClick={onRePW}
        >忘记密码</Button>
      </FlexItem>
    </Flex>
    <Button type="primary" htmlType="submit" className={styles.Button}
      loading={loading}
    >登陆</Button>
  </Form>
}