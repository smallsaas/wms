import React, { useRef, useMemo, useState, useEffect } from 'react';
import { Form } from 'antd';
import { history } from 'umi';
import { formatAPI } from 'zero-element/lib/utils/format';
import useBaseForm from 'zero-element/lib/helper/form/useBaseForm';
import { useDidMount, useWillUnmount, useForceUpdate } from 'zero-element/lib/utils/hooks/lifeCycle';
import { Spin, Button, message } from 'antd';
import { getFormItem } from 'zero-element-antd/lib/utils/readConfig';
import { Render } from 'zero-element/lib/config/layout';
import global from 'zero-element/lib/config/global';
import useFormHandle from 'zero-element-antd/lib/container/Form/utils/useFormHandle';
import extraFieldType from 'zero-element-antd/lib/container/Form/utils/extraFieldType';
import canPortal from 'zero-element-antd/lib/utils/canPortal';
import { setPageData, getPageData, clearPageData, getHooks } from 'zero-element/lib/Model';

import promiseAjax from '@/utils/promiseAjax';
import { get as getEndpoint } from 'zero-element/lib/utils/request/endpoint';

import './auditForm.less';

const defaultLabelCol = {
  xs: { span: 8, },
};
const defaultWrapperCol = {
  // xs: { span: 16, },
};
export default function BaseForm(props) {
  const [form] = Form.useForm();

  const forceUpdate = useForceUpdate();
  const {
    MODAL, namespace, config, extraData = {},
    onClose, onSubmit, extraEl,
    loading: propsLoading,
    forceInitForm,
    footer,
    hooks = {},
    formRef,
    keepData = false, // 卸载 BaseForm 时不销毁 model.formData
  } = props;
  const {
    API = {},
    layout = 'Empty', layoutConfig = {},
    fields: fieldsCfg,
    otherProps={footerButton:true},
    path,
    goBack: gobackOpt = true,
    footer: footerOpt,
    requestOptions,
  } = config;
  const { layoutType = 'inline' } = layoutConfig; // inline vertical horizontal
  const formProps = useBaseForm({
    namespace,
    modelPath: 'formData',
    extraData,
  }, config);
  const { router, goBack } = global;

  const renderGoBack = extraEl && extraEl.current && goBack;

  const { loading, data, model, handle } = formProps;
  const { onGetData, onFormMap } = getHooks(namespace);
  const pageDataFormData = getPageData(namespace).formData;

  const initData = useRef({
    ...extraData,
    ...pageDataFormData,
    ...data,
  });

  const [ currentId, setCurrentId ] = useState('');
  const [ auditStatus, setAuditStatus ] = useState('')

  const {
    onFormatValue,
    handleFormatValue,
    onSaveOtherValue,
    onValuesChange,
    onExpect,
  } = useFormHandle(form, {
    namespace,
    config,
    forceInitForm,
    onGetOne: handleGetData,
  });
  const extraFields = useRef([]);
  const [fields, setFields] = useState(fieldsCfg);
  const { onGetOne, onCreateForm, onUpdateForm, onClearForm } = handle;
  const [canRenderForm, setCanRenderForm] = useState(API.getAPI ? false : true);

  // useMemo(recordDefaultValue, [fields]);
  useDidMount(_ => {
    recordDefaultValue();
    if (API.getAPI) {
      handleGetData();
    }
    if (typeof formRef === 'object') {
      formRef.current = form;
    }
  });

  useWillUnmount(_ => {
    // if (!keepData) {
    if (!keepData || MODAL) {
      onClearForm();
      clearPageData(namespace, 'formData');
    }
  });

  function handleGetData() {
    setCanRenderForm(false);
    onGetOne({}).then((response) => {
      const { code, data } = response || {};
      if (code === 200 && data) {
        let formData = data;
        if (typeof onGetData === 'function') {
          formData = onGetData(data);
        }

        initData.current = formData;
        const { extra, images, tags } = formData;
        setPageData(namespace, 'formData', formData);
        form.setFieldsValue({ ...initData.current });

        //保存ID
        setCurrentId(data.id)

        if (Array.isArray(images) || Array.isArray(tags)) {
          const otherField = [];

          if (Array.isArray(images)) {
            otherField.push({ lable: '', field: 'tags', type: 'tags' });
          }
          if (Array.isArray(images)) {
            otherField.push({ lable: '', field: 'images', type: 'upload-image' });
          }

          setFields([
            ...fields,
            ...otherField,
          ]);
        }
        if (extra && Array.isArray(extra.items)) {
          setExtraFields(extra.items);
        } else {
          forceUpdate();
        }
      }
    })
      .finally(_ => {
        setCanRenderForm(true);
      })
  }
  function setExtraFields(items) {
    setFields([
      ...fields,
      ...items.map((item, i) => {
        extraFields.current.push(item.attr);
        return {
          label: item.fieldName,
          field: ['extra', 'items', String(i), 'value'],
          type: extraFieldType[item.fieldType] || 'input',
          value: ['extra', 'items', String(i), 'value'],
        }
      }),
    ]);
  }

  function recordDefaultValue() {
    fields.forEach(item => {
      const { field, value } = item;
      if (value !== undefined && initData.current[field] === undefined) {
        initData.current[field] = value;
      }
    });
    form.setFieldsValue({ ...initData.current });
    forceUpdate();
  }

  function handleSubmitForm(values) {
  }

  function handleResponse(data = {}, opt = {}) {
    const { message: msg = '操作成功' } = opt;
    if (data.code === 200) {
      if (msg) {
        message.success(msg);
      }
      if (onClose) {
        onClose(data);
      }
      if (router) {
        if (path) {
          const fPath = formatAPI(path, {
            namespace,
          });
          router(fPath);
          return false;
        }
      }
      if (!MODAL && gobackOpt && goBack) {
        goBack();
      }
    } else {
      message.error(`审核失败: ${data.message}`);
    }
  }

  function handleGoBack() {
    if (path) {
      const fPath = formatAPI(path, {
        namespace,
      });
      router(fPath);
    } else {
      goBack();
    }
  }

  // function handleReset() {
  //   form.resetFields();
  //   forceUpdate();
  // }

  function renderFooter() {
    function onSubmit(status) {
        let apiPath = '';

        if(status === 'pass'){
            apiPath = API.passAPI;
        }else if(status === 'refuse'){
            apiPath = API.refuseAPI;
        }

        apiPath = apiPath.replace('[id]', currentId)

        promiseAjax(getEndpoint()+apiPath, initData.current, {method: 'PUT'}).then(handleResponse)
        // form.submit();
    }

    if (footer !== undefined || footerOpt !== undefined) {
      return footer;
    }

    const classes = MODAL ? 'ant-modal-footer' : 'ZEle-Form-footer';
    return <div className={classes}>
        <Button type="primary" htmlType="submit" onClick={()=>onSubmit('pass')}>审核通过</Button>
        <Button onClick={()=>onSubmit('refuse')}>审核拒绝</Button>
    </div>
  }

  return <Spin spinning={propsLoading || loading}>
    {renderGoBack && canPortal(extraEl, <Button onClick={handleGoBack}>返回</Button>)}
    <div className={fields.length ? 'ant-modal-body' : undefined}>
      {canRenderForm ? (
        <Form
          form={form}
          layout={layoutType}
          labelCol={defaultLabelCol}
          wrapperCol={defaultWrapperCol}
          initialValues={initData.current}
          onValuesChange={onValuesChange}
          onFinish={handleSubmitForm}
        >
          <Render n={layout} {...layoutConfig}>
            {fields.map(field => getFormItem(field, model, {
              namespace,
              form,
              handle: {
                onFormatValue,
                onSaveOtherValue,
                onExpect,
              },
              hooks,
              extraData,
            }))}
          </Render>
        </Form>
      ) : <Form form={form} />}
    </div>
    {!otherProps ? renderFooter() : otherProps.footerButton?renderFooter():null}
  </Spin>
}