import React, { useEffect } from 'react';
import { formatAPI } from 'zero-element/lib/utils/format';

export default (props) => {
  const {
    name,
    namespace,
    props: propsOtp,
    defaultValue,
    value = defaultValue,
    options,
    formdata,
    onChange,
    ...rest
  } = props;
  const {
    placeholder = '-',
    autoSave = false,
    format, map,
    symbol = '/', joinField,
  } = options;

  let v = value;
  if (format) {
    const rst = [];

    if (Array.isArray(format)) {
      format.forEach(f => {
        rst.push(formatAPI(f, { namespace, placeholder, data: useRecordData(f,formdata) }))
      })
    } else {
      rst.push(formatAPI(format, { namespace, placeholder, data: useRecordData(format,formdata) }));
    }
    v = rst.join('\n');
  }
  if (map) {
    v = map[v] || v;
  }

  if (Array.isArray(v)) {
    if (joinField) {
      v = v.map(i => i[joinField]).join(symbol);
    } else {
      v = v.join(symbol);
    }
  }

  useEffect(_ => {
    if (autoSave && v !== value) {
      onChange(v);
    }
  }, [autoSave, value]);

  let echoText = v;
  if (!v && v !== 0) {
    echoText = placeholder;
  }

  function useRecordData(field, record){
    if(field && field.indexOf('.') != -1){
      const recordField = field.split('.')[0].substring(0, 1)
      return record[recordField]
    }else{
      return record
    }
  }
  //
  if(name && name.indexOf('.') != -1){
    echoText = formdata[name.split('.')[0]][name.split('.')[1]]
  }

  return <div {...rest}{...propsOtp}>{echoText}</div>;
}