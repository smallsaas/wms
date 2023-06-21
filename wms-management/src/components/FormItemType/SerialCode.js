import React, { useState, useEffect } from 'react';
import { Button, Space, Input } from 'antd';
import { RedoOutlined } from '@ant-design/icons';

export default ({ props, onChange,  ...rest }) => {

  const { prefixValue='' } = props;
  const { namespace, name, formdata, value } = rest;
  const [ inputValue, setInputValue ] = useState('');

  useEffect(_ => {
    namespace && namespace.indexOf('add') != -1 && generateNumber()
  }, [])

  function generateNumber () {
      var currentDate = new Date;
      var year = currentDate.getFullYear(); //获取当前年
      var mon = currentDate.getMonth() + 1; //获取当前月
      var date = currentDate.getDate(); //获取当前日
      var hours = currentDate.getHours(); //获取当前小时
      var minutes = currentDate.getMinutes(); //获取当前分钟
      var seconds = currentDate.getSeconds(); //获取当前秒
      var value = `${prefixValue}${year}${mon < 10 ? `0${mon}`: mon}${date < 10 ? `0${date}`: date}${hours < 10 ? `0${hours}`: hours}${minutes < 10 ? `0${minutes}`: minutes}${seconds < 10 ? `0${seconds}`: seconds}`;
      
      setInputValue(value)
      onChange(value)
  }

  function refresh () {
    generateNumber()
  }

  return (
    <Space>
        <Input disabled {...rest} {...props} style={{ width:"240px" }} defaultValue={inputValue || value} />
        { namespace && namespace.indexOf('add') != -1 && <Button shape="circle" icon={<RedoOutlined />} onClick={() => refresh()} /> }
        
    </Space>
  );
}