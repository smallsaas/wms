import React, { useEffect, useState } from 'react';
import { formatAPI } from 'zero-element/lib/utils/format';
import './index.less';

export default function valueTypePlain(props) {
  const { namespace, options = {}, data: { index, text = '', record }, } = props;
  const { style, format='-', placeholder = '-' } = options;
  const [t, setT] = useState('');

  useEffect(_ => {
    if(text && text.indexOf('[') != -1){
      const data = JSON.parse(text);
      if(data.length > 0){
        setT(data.join(format))
      }else{
        setT('-')
      }
    }
  }, []);
  
  let echoText = t;
  if (!text && text !== 0) {
    echoText = placeholder;
  }
  return <span style={style}>{echoText}</span>
}