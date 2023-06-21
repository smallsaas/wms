import React, { useEffect, useState } from 'react';
import { formatAPI } from 'zero-element/lib/utils/format';
import lodash_ from 'lodash'
import './index.less';

export default function valueTypePlain(props) {
  const { namespace, options = {}, field,  data: { index, text = '', record }, } = props;
  const { style, format, placeholder = '-' } = options;
  const [t, setT] = useState([]);

  useEffect(_ => {

    if (format) {
      const rst = [];
      if (Array.isArray(format)) {
        format.forEach(f => {
          rst.push(formatAPI(f, { namespace, placeholder, data: useRecordData(f, record) }))
        })
      } else {
        rst.push(formatAPI(format, { namespace, placeholder, data: useRecordData(format, record) }))
      }
      setT(rst);

    } else {
      let v = text
      if(field && field.indexOf('.') != -1){
        v = lodash_.get(record, field, "-")
      }
      setT([String(v)]);
    }
  }, [record, format]);

  function useRecordData(field, record){
    if(field && field.indexOf('.') != -1){
      const recordField = field.split('.')[0].substring(0, 1)
      return record[recordField]
    }else{
      return record
    }
  }

  if (format) {
    return <div>
      {t.map((text, i) => {
        const rst = /^(?<label>\S+(:|：))(?<value>[\ \S]+)/.exec(text);
        if (rst && rst.groups) {
          const { groups } = rst;
          const { label, value } = groups;
          return <div className="ZEle-valueType-plain" key={i}>
            <span className="label">{label}</span>
            <span>{value}</span>
          </div>
        }
        return <div className="ZEle-valueType-plain" key={i}>{text}</div>;
      })}
    </div>
  } else if (field && field.indexOf('.') != -1) {
    return <span style={style}>{t}</span>
  }

  let echoText = text;
  if (!text && text !== 0) {
    echoText = placeholder;
  }
  return <span style={style}>{echoText}</span>
}