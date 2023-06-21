import React from 'react';
import DetailsTemplate from '@/components/Details/DetailsTemplate';
import setting from './config/issueTask-setting';
    
export default () => {

  return <DetailsTemplate
    namespace="issueTask_view"
    setting={setting}
    config={setting.viewConfig}
  />
}
    
