// React
import PropTypes from "prop-types";

//Libraries
// the hoc
import { withTranslation } from "react-i18next";

import { useForm } from "react-hook-form";

const MockyFormComponent = ({ t }) => {
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm();
  const onSubmit = (values) => console.log(values);

  return (
    <>
      <div className="card border-primary m-5 p-4">
        <div className="card-body">
          <div className="row">
            <div className="col">
              <div className="card-title text-center">
                <h3>{t("designMocks")}</h3>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col">
              <div className="card-subtitle mb-2 text-center text-muted">
                {t("anyRequest")}
              </div>
            </div>
          </div>
          <form>
            <div className="row g-2">
              <div className="col col-md-6 mb-3">
                <label className="form-label" htmlFor="selectStatus">
                  {t("formHTTPStatus")}
                </label>
                <select
                  id="selectStatus"
                  className="form-select"
                  defaultValue="200"
                  {...register("status", {
                    required: "Required",
                    pattern: {
                      message: "Need to select one option",
                    },
                  })}
                  aria-label="Default select example"
                >
                  <option disabled value>
                    {" "}
                    1xx Informational Response
                  </option>
                  <option value="100">100 - Continue</option>
                  <option disabled value>
                    {" "}
                    2xx Success
                  </option>
                  <option value="200">200 - Ok</option>
                  <option disabled value>
                    {" "}
                    4xx Client Errors
                  </option>
                  <option value="404">404 - Not Found</option>
                  <option disabled value>
                    {" "}
                    5xx Server Errors
                  </option>
                  <option value="500">500 - Internal Server Error</option>
                </select>
                {errors.status && errors.status.message}
                <div className="form-text text-primary">
                  {t("formHTTPStatusHelp")}
                </div>
              </div>
            </div>
            <div className="row g-2">
              <div className="col col-md-6">
                <div className="mb-3">
                  <label htmlFor="selectContentType">
                    {t("formContentType")}
                  </label>
                  <select
                    id="selectContentType"
                    className="form-select"
                    aria-label="Default select example"
                    required
                  >
                    <option value="1">application/json</option>
                    <option value="2">text/html</option>
                    <option value="3">text/js</option>
                    <option value="4">text/css</option>
                    <option value="5">text/json</option>
                    <option value="6">text/plain</option>
                  </select>
                  <div className="form-text text-primary">
                    {t("formContentTypeHelp")}
                  </div>
                </div>
              </div>
              <div className="col col-md-6">
                <div className="mb-3">
                  <label htmlFor="selectCharset">{t("formCharset")}</label>
                  <select
                    id="selectCharset"
                    className="form-select"
                    aria-label="Default select example"
                    required
                  >
                    <option value="1">UTF-8</option>
                    <option value="2">ISO-8859-1</option>
                    <option value="3">UTF-16</option>
                  </select>
                  <div className="form-text text-primary">
                    {t("formCharsetHelp")}
                  </div>
                </div>
              </div>
            </div>
            <div className="row g-2">
              <div className="col col-md-12">
                <div className="mb-3">
                  <label htmlFor="textAreaHeaders">{t("formHeaders")}</label>
                  <textarea
                    id="textAreaHeaders"
                    className="form-control"
                    rows={4}
                    placeholder={
                      '"{\n' + '  "X-Foo-Bar": "Hello World"\n' + '}"'
                    }
                  />
                  <div className="form-text text-primary">
                    {t("formHeadersHelp")}
                  </div>
                </div>
              </div>
            </div>
            <div className="row g-2">
              <div className="col col-md-12">
                <div className="mb-3">
                  <label htmlFor="textAreaResponseBody">
                    {t("formResponseBody")}
                  </label>
                  <textarea
                    id="textAreaResponseBody"
                    className="form-control"
                    rows={12}
                    placeholder={
                      '"{\n' +
                      '  "identity": {\n' +
                      '    "id": "b06cd03f-75d0-413a-b94b-35e155444d70",\n' +
                      '    "login": "John Doe"\n' +
                      "  },\n" +
                      '  "permissions": {\n' +
                      '    "roles": [\n' +
                      '      "moderator"\n' +
                      "    ]\n" +
                      "  }\n" +
                      '}"'
                    }
                  />
                  <div className="form-text text-primary">
                    {t("formHTTPStatusHelp")}
                  </div>
                </div>
              </div>
            </div>
            <div className="row justify-content-md-center">
              <div className="col col-lg-3 col-xl-3">
                <button
                  className="btn btn-primary"
                  type="submit"
                  onClick={(e) => {
                    e.preventDefault();
                    handleSubmit(onSubmit);
                  }}
                >
                  {t("formSubmit")}
                </button>
              </div>
              <div className="col col-lg-3 col-xl-3">
                <select
                  className="form-select"
                  aria-label="Default select example"
                  required
                >
                  <option value="1">Never expire</option>
                  <option value="2">Expire in 1 year</option>
                  <option value="3">Expire in 1 Month</option>
                  <option value="4">Expire in 1 week</option>
                  <option value="5">Expire in 1 day</option>
                  <option value="6">Expire in 1 hour</option>
                  <option value="7">Expire in 1 minute</option>
                </select>
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

MockyFormComponent.propTypes = {
  t: PropTypes.any,
};

export default withTranslation()(MockyFormComponent);
