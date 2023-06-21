import React, { useState } from 'react';
import { Form, Input, Button } from 'antd';
import { PhoneOutlined, KeyOutlined } from '@ant-design/icons';

import Captcha from './Captcha';
import styles from '../index.less';

export default function RetrieveFromPhone(props) {
  const { loading, onReFPhone } = props;
  const [phone, setPhone] = useState();

  function handleChange(data, allData) {
    setPhone(allData.phone);
  }

  return <Form
    onFinish={onReFPhone}
    className={styles.Form}
    onValuesChange={handleChange}
  >
    <Form.Item name="phone" rules={[{ required: true, message: '请输入手机号' }]}>
      <Input
        prefix={<PhoneOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
        placeholder="手机号码"
      />
    </Form.Item>
    <Form.Item name="validateCode" rules={[{ required: true, message: '请输入短信验证码' }]}>
      <Captcha
        type="phone"
        receiver={phone}
      />
    </Form.Item>
    <Form.Item name="newPassword" rules={[{ required: true, message: '请输入新密码' }]}>
      <Input
        prefix={<KeyOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
        placeholder="新密码"
      />
    </Form.Item>
    <Form.Item>
      <Button type="primary" htmlType="submit" className={styles.Button}
        loading={loading}
      >
        重置密码
    </Button>
    </Form.Item>
  </Form>
}