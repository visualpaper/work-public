import React from 'react';
import bigInt from 'big-integer'
import { Table, Button, Input } from 'reactstrap';
import { withFormik, Form, Field, FieldArray, getIn } from 'formik';

const defaultData = {
  id: 0,
  bytes: 10,
  hex: "9223372036854775807",
}

// Components (とりあえずでここに置く)
const AddButton = ({ field, form, ...props }) => (
  <Button
    type="button"
    onClick={() => props.arrayHelpers.remove(props.dataIndex)}>
    -
  </Button>
)

const TextFieldWithFormik = ({ field, form }) => {
  const error = getIn(form.errors, field.name);

  return (
    <>
      <Input
        id={field.name}
        label=""
        defaultValue={field.value}
        onChange={field.onChange}
        invalid={(error !== undefined)}
        errormessage={error} />
      <div>{error}</div>
    </>
  )
}

// 便利関数
const isNumeric = (value) => {
  return (/^\d+$/).test(value)
}

const isEmpty = (value) => {
  return !value || value === "";
}


// Validater
/**
 * - 必須
 * - 0 ～ 299 範囲内である
 * 
 * @param {*} value 
 */
const idValidator = (value) => {

  if (isEmpty(value)) {
    return 'Required';
  }

  if (!isNumeric(value) || value < 0 || 299 < value) {
    return 'Please enter a value within 0-299';
  }
}


/**
 * - -9223372036854775808 ～ 9223372036854775807 範囲内である
 * 
 * @param {*} value 
 */
const hexValidator = (value) => {

  if (isEmpty(value)) {
    return;
  }

  if (
    bigInt(value).lesser(bigInt("-9223372036854775808")) ||
    bigInt(value).greater(bigInt("9223372036854775807"))
  ) {
    return 'Please enter a value within -9223372036854775808-9223372036854775807';
  }
}

/**
 * - 189 バイト以下
 */
const bytesValidator = (value) => {

  if (isEmpty(value)) {
    return;
  }

  if (new Blob([value]).size > 189) {
    return 'Please enter UTF-8 string up to 189 bytes';
  }
}


class FieldArraySample extends React.Component {

  render() {
    const { values } = this.props;

    return (
      <Form>
        <FieldArray
          name="datas"
          render={arrayHelpers => (
            <>
              <Table striped>
                <thead>
                  <tr>
                    <th>-</th>
                    <th>ID</th>
                    <th>BYTES</th>
                    <th>HEX</th>
                  </tr>
                </thead>
                <tbody>
                  {values.datas.map((data, index) => (

                    <tr key={index}>
                      <td><Field arrayHelpers={arrayHelpers} dataIndex={index} component={AddButton} /></td>
                      <td><Field name={`datas.${index}.id`} validate={idValidator} component={TextFieldWithFormik} /></td>
                      <td><Field name={`datas.${index}.bytes`} validate={bytesValidator} component={TextFieldWithFormik} /></td>
                      <td><Field name={`datas.${index}.hex`} validate={hexValidator} component={TextFieldWithFormik} /></td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              <Button onClick={() => arrayHelpers.push(toFormValue(defaultData))}>+</Button>
              <Button onClick={() => window.location.reload()}>Reset</Button>
              <Button type="submit">Send to Server</Button>
            </>
          )}
        />
      </Form>
    )
  }
}

const toFormValue = (data) => {

  return {
    id: String(data.id),
    bytes: String(data.bytes),
    hex: data.hex
  }
}

const allValidator = (values) => {
  let errors = {};

  errors.datas = values.datas.map((value, index, self) => {

    if (isDuplicatedId(self, value.id)) {
      return { id: "duplicated" }
    }
    return {}
  })

  return errors;
}

const isDuplicatedId = (values, id) => {
  return values.filter((value) => value.id === id).length > 1;
}

const MyEnhancedForm = withFormik({

  mapPropsToValues: ({ datas }) => {
    if (datas) {
      return { datas: datas.map((value) => toFormValue(value)) };
    }
    return { datas: [] }
  },

  enableReinitialize: true,

  validate: allValidator,

  handleSubmit: (values) => {
    console.log(values)
  },

  displayName: 'BasicForm',
})(FieldArraySample);

export default MyEnhancedForm;
