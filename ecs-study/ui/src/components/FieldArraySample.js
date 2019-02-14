import React from 'react';
import { Formik, FieldArray, Form, Field, getIn } from 'formik';
import { Table, Button, Input } from 'reactstrap';

const initialBoards = [
  {
    id: "1"
  },
  {
    id: 2
  }
]

function allValidate(values) {
  let errors = {};

  console.log(values)
  // 常に発火。error が上書きされる
  errors.boards = [
    { id: "aaaa" }
  ]
  console.log("allValidate")

  return errors;
}

function validateUsername(value) {
  let error;
  console.log("checkUserName");
  if (value === 'admin') {
    error = 'Nice try!';
  }
  return error;
}

const ErrorMessage = ({ field, form, ...props }) => {
  const error = getIn(form.errors, props.validationKey);

  return error ? error : null;
};

export default () => (
  <div>
    <Formik
      enableReinitialize
      validate={allValidate}
      validateOnChange
      initialValues={{ boards: initialBoards }}
      onSubmit={values => {
        console.log(values);
      }}
      render={({ values }) => (
        <Form>
          <FieldArray
            name="boards"
            render={arrayHelpers => (
              <>
                <Table striped>
                  <thead>
                    <tr>
                      <th className="col-xs-1">aaaaa</th>
                      <th className="col-xs-1">bbbb</th>
                      <th className="col-xs-1">aaaaa</th>
                      <th className="col-xs-1">bbbb</th>
                    </tr>
                  </thead>
                  <tbody>
                    {values.boards.map((board, index) => (

                      <tr key={index}>
                        <td><Field
                          arrayHelpers={arrayHelpers}
                          index={index}
                          component={({ field, form, ...innerProps }) => (
                            <Button type="button" onClick={() => innerProps.arrayHelpers.remove(innerProps.index)}>-</Button>
                          )
                          }
                        /></td>
                        <td><Field
                          name={`boards.${index}.id`}
                          validate={validateUsername}
                          /*
                          component={({ field, form }) => (
                            <Input
                              id={field.name}
                              defaultValue={field.value}
                              onBlur={e => {
                                form.setFieldValue(field.name, e.target.value)
                              }
                            }
                          */
                          render={({ field, form }) => (
                            <Input
                              id={field.name}
                              defaultValue={field.value}
                              onChange={field.onChange}
                            />
                          )}
                        />
                          <Field validationKey={`boards.${index}.id`} component={ErrorMessage} />
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
                <Button onClick={() => { arrayHelpers.push({ id: 0 }) }}>+</Button>
                <Button onClick={() => window.location.reload()}>Reset</Button>
                <Button type="submit">Send to Server</Button>
              </>
            )}
          />
        </Form>
      )}
    >
    </Formik>
  </div>
)