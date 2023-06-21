import React, { useState } from 'react';
import { Form, Input, Button } from 'antd';
import { PhoneOutlined, LockOutlined } from '@ant-design/icons';

import styles from '../index.less';
import Captcha from './Captcha';

export default function CaptchaLogin(props) {
  const { onCaptchaLogin, loading } = props;
  const [phone, setPhone] = useState();

  function handleChange(data) {
    setPhone(data.account);
  }


  return (
    <Form
      onFinish={onCaptchaLogin}
      className={styles.Form}
      onValuesChange={handleChange}
    >
      <Form.Item name="account" rules={[{ required: true, message: '请输入手机号' }]}>
        <Input
          prefix={<PhoneOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
          placeholder="手机号"
        />
      </Form.Item>
      <Form.Item name="validateCode" rules={[{ required: false, message: '请输入验证码' }]}>
        <Captcha
          type="phone"
          receiver={phone}
        />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" className={styles.Button}
          loading={loading}
        >
          登录
        </Button>
      </Form.Item>
    </Form>
  )
}