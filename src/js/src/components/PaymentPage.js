import React, { useContext } from 'react';
import { Select, Button, Row, InputNumber, Col, Tag, Icon, Spin } from 'antd';
import { AccountContext } from '../contexts/AccountContext';
import { Formik, Form, Field } from 'formik';
import { transferMoney } from '../client';

export const PaymentPage = () => {
  const inputBottomMargin = { marginBottom: '10px' };
  const tagStyle = {
    backgroundColor: '#f50',
    color: 'white',
    ...inputBottomMargin
  };

  const { accounts, loading } = useContext(AccountContext);

  const { Option } = Select;

  const getIndicatorIcon = () => (
    <Icon type='loading' className='spinner' spin />
  );

  function SelectedFromAccount(fieldProps) {
    return (
      <Select
        placeholder='Select FROM account'
        style={{ width: 220 }}
        onChange={option =>
          fieldProps.form.setFieldValue(fieldProps.field.name, option)
        }
      >
        {accounts.map(item => {
          return (
            <Option key={item.id} value={item.id}>
              {item.name}
            </Option>
          );
        })}
      </Select>
    );
  }

  function SelectedToAccount(fieldProps) {
    return (
      <Select
        placeholder='Select TO account'
        style={{ width: 220 }}
        onChange={option =>
          fieldProps.form.setFieldValue(fieldProps.field.name, option)
        }
      >
        {accounts.map(item => {
          return (
            <Option key={item.id} value={item.id}>
              {item.name}
            </Option>
          );
        })}
      </Select>
    );
  }

  function SelectedAmount(fieldProps) {
    return (
      <InputNumber
        name='amount'
        min={0}
        max={1000000}
        onChange={option =>
          fieldProps.form.setFieldValue(fieldProps.field.name, option)
        }
      />
    );
  }

  return (
    <div>
      {loading ? (
        <Spin indicator={getIndicatorIcon()} />
      ) : (
        <Formik
          initialValues={{ fromAccountId: '', toAccountId: '', amount: '' }}
          validate={values => {
            let errors = {};
            if (!values.fromAccountId) {
              errors.fromAccountId = 'From Account Required';
            }
            if (!values.toAccountId) {
              errors.toAccountId = 'To Account Required';
            }
            if (!values.amount) {
              errors.amount = 'Amount Required';
            }
            return errors;
          }}
          onSubmit={(input, { setSubmitting}) => {
            transferMoney(input)
              .then(() => {
                window.location.reload();
              })
              .catch(err => {
                console.log('errrror');
              })
              .finally(() => {
                setSubmitting(false);
              });
           
          }}
        >
          {({ errors, touched, handleSubmit, submitForm }) => (
            <div>
              <h1 className='py-5'>Payment Page</h1>
              <Form onSubmit={handleSubmit}>
                <Row>
                  <Col span={8}>
                    <Field
                      name='fromAccountId'
                      component={SelectedFromAccount}
                    />
                    <div>
                      {errors.fromAccountId && touched.fromAccountId && (
                        <Tag style={tagStyle}>{errors.fromAccountId}</Tag>
                      )}
                    </div>
                  </Col>
                  <Col span={8}>
                    <Field name='toAccountId' component={SelectedToAccount} />
                    <div>
                      {errors.toAccountId && touched.toAccountId && (
                        <Tag style={tagStyle}>{errors.toAccountId}</Tag>
                      )}
                    </div>
                  </Col>
                  <Col span={8}>
                    Amount:
                    <Field name='amount' component={SelectedAmount} />
                    <span>$ </span>
                    <div>
                      {errors.amount && touched.amount && (
                        <Tag style={tagStyle}>{errors.amount}</Tag>
                      )}
                    </div>
                  </Col>
                </Row>
                <Row className='py-5'>
                  <Button
                    onClick={() => submitForm()}
                    type='primary'
                    ghost
                    block
                  >
                    Submit
                  </Button>
                </Row>
              </Form>
            </div>
          )}
        </Formik>
      )}
    </div>
  );
};

export default PaymentPage;
