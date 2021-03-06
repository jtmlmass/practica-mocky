import { authPath as path } from "../core/api/path";
import { restClient } from "../core/api/restClient";

/**
 * @description Service function for authentication
 * @param {string} username
 * @param {string} password
 * @returns {Promise<any>}
 */
export const authUser = data => {
	const route = path.authUser();
	return restClient(route, "POST", JSON.stringify(data))
		.then(function (response) {
			return Promise.resolve(response);
		})
		.catch(error => {
			return Promise.reject(error);
		});
};

/**
 * @description Function to get username information from local browser storage
 * @returns {string}
 */
export const getUsername = () => {
	return localStorage.getItem("username");
};

// Function to register username and token to the local browser storage
/**
 * @param {string} username
 * @param {string} token
 */
export const setAuthInformation = (username, token) => {
	localStorage.setItem("username", username);
	localStorage.setItem("token", token);
};

// Function logout to delete username and token information from the local browser storage
/**
 * @param {string} username
 * @param {string} token
 */
export const logOut = () => {
	localStorage.removeItem("username");
	localStorage.removeItem("token");
};
