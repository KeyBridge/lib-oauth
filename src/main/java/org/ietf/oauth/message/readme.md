Request for Comments: 6749

The OAuth 2.0 Authorization Framework

The OAuth 2.0 authorization framework enables a third-party
application to obtain limited access to an HTTP service.

1.2.  Protocol Flow

<pre>
  +--------+                               +---------------+
  |        |--(A)- Authorization Request ->|   Resource    |
  |        |                               |     Owner     |
  |        |<-(B)-- Authorization Grant ---|               |
  |        |                               +---------------+
  |        |
  |        |                               +---------------+
  |        |--(C)-- Authorization Grant -->| Authorization |
  | Client |                               |     Server    |
  |        |<-(D)----- Access Token -------|               |
  |        |                               +---------------+
  |        |
  |        |                               +---------------+
  |        |--(E)----- Access Token ------>|    Resource   |
  |        |                               |     Server    |
  |        |<-(F)--- Protected Resource ---|               |
  +--------+                               +---------------+
</pre>

1.3.4.  Client Credentials

   The client credentials (or other forms of client authentication) can
   be used as an authorization grant.

1.4.  Access Token

   Access tokens are credentials used to access protected resources.  An
   access token is a string representing an authorization issued to the
   client.  

Case 1: Retrieve a new access token.

The token request 
4.1.3.  Access Token Request
  client_id is OAuth consumer key
  scope        is the scope request
  nonce        is a one-time nonce; written into the redirect URI field
  state        is the nonce; hased using the shared secret
               e.g. DigestUtils.sha512Hex(secret + data);

4.2.2.  Access Token Response
  access_token is the token
  expires_in   is the duration
  scope        is the scope grant
  token_type   is "OAUTH"

<pre>
  +--------+                               +---------------+
  |        |--(G)----- Token Request ----->| Authorization | GET token/
  | Client |                               |     Server    |
  |        |<-(H)----- Access token -------|               |
  +--------+                               +---------------+
</pre>

DEPRECATED - just get a new access token!

1.5.  Refresh Token

   Refresh tokens are credentials used to obtain access tokens.  Refresh
   tokens are issued to the client by the authorization server and are
   used to obtain a new access token when the current access token
   becomes invalid or expires.

Case 2: Refresh an existing access token.

<pre>
  +--------+                               +---------------+
  |        |--(I)----- Access Token ------>| Authorization | GET refreshtoken/
  | Client |                               |     Server    |
  |        |<-(J)--- Refreshed token ------|               |
  +--------+                               +---------------+
</pre>


2.1.  Client Types

   * **confidential** - capable of maintaining the confidentiality of their credentials (e.g., client implemented on a secure server)
   * **public** -  incapable of maintaining the confidentiality of their credentials (e.g., clients executing on an insecure device)

3.  Protocol Endpoints

   *  **Authorization endpoint** - used by the client to obtain authorization from the resource owner via user-agent redirection.
   *  **Token endpoint** - used by the client to exchange an authorization grant for an access token, typically with client authentication.