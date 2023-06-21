import React from 'react';
import './IconInput.less';

export default function IconInput(props) {
  const { icon, type, extra, placeholder, onChange } = props;

  return <div className="c-IconInput">
    <span className="icon">
      <img src={icon} />
    </span>
    <span className="line"></span>
    <input type={type} placeholder={placeholder} onChange={onChange} />
    {extra}
  </div>
}