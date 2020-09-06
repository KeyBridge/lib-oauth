# lib-oauth

**OAuth 2.0 Authorization Framework**

Provides a library of funtional primitives upon which a standards-compliant implementaiton of an OAuth 2.0 client and server can be developed. 

The OAuth 2.0 authorization framework enables a third-party
application to obtain limited access to an HTTP service, either on
behalf of a resource owner by orchestrating an approval interaction
between the resource owner and the HTTP service, or by allowing the
third-party application to obtain access on its own behalf.

This OAuth library includes data objects specified in several RFCs:

  *  [5849](https://tools.ietf.org/html/rfc5849)  The OAuth 1.0 Protocol  **deprecated**
  *  [6749](https://tools.ietf.org/html/rfc6749)  The OAuth 2.0 Authorization Framework
  *  [6750](https://tools.ietf.org/html/rfc6750)  The OAuth 2.0 Authorization Framework: Bearer Token Usage
  *  [6819](https://tools.ietf.org/html/rfc6819)  OAuth 2.0 Threat Model and Security Considerations
  *  [7662](https://tools.ietf.org/html/rfc7662)  OAuth 2.0 Token Introspection
  *  [7591](https://tools.ietf.org/html/rfc7591)  OAuth 2.0 Dynamic Client Registration Protocol
  *  [7592](https://tools.ietf.org/html/rfc7592)  OAuth 2.0 Dynamic Client Registration Management Protocol
  *  [8628](https://tools.ietf.org/html/rfc8628)  OAuth 2.0 Device Authorization Grant

**Road map**

The following specifications are planned for implementation in this library.

  *  [7009](https://tools.ietf.org/html/rfc7009)  OAuth 2.0 Token Revocation
  *  [8252](https://tools.ietf.org/html/rfc8252)  OAuth 2.0 for Native Apps
  *  [8628](https://tools.ietf.org/html/rfc8628)  OAuth 2.0 Device Authorization Grant

**Not implemented**

The following specifications are noted but not planned for implementation in this library.

  *  [7521](https://tools.ietf.org/html/rfc7521)  Assertion Framework for OAuth 2.0 Client Authentication and Authorization Grants
  *  [7522](https://tools.ietf.org/html/rfc7522)  Security Assertion Markup Language (SAML) 2.0 Profile for OAuth 2.0
  *  [7636](https://tools.ietf.org/html/rfc7636)  Proof Key for Code Exchange by OAuth Public Clients
Client Authentication and Authorization Grants
  *  [draft-ietf-oauth-token-binding](https://tools.ietf.org/html/draft-ietf-oauth-token-binding-08)  OAuth 2.0 Token Binding

**Authentication and session management**

The OAuth framework only describes an authorization method. It does not provide details about the user. OpenID Connect is a simple identity layer on top of the OAuth 2.0 protocol. The [OpenID Connect](https://openid.net/connect/) profile is implemented separately in the Key Bridge `lib-openid` project.

  * [OpenID Connect](https://openid.net/connect/) OpenID Connect 1.0 specification

**Encryption and signing**

The OAuth framework does not describe an encryption and signing strategy, nor does it support self asserted claims. The JSON Web Token specification provides a crypographically secure layer for the OAuth 2.0 protocol. The [JSON Web Token](https://tools.ietf.org/html/rfc7523) profile is implemented separately in the Key Bridge `lib-jose` project.

  * [7523](https://tools.ietf.org/html/rfc7523)  JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication and Authorization Grants

## Bootstrapping

Before they can authenticate end users, OAuth 2.0 clients must first register with
the OAuth authorization server. This is implemented via the [Dynamic Client Registration Protocol](https://tools.ietf.org/html/rfc7591).

<pre>
        +--------(A)- Initial Access Token (OPTIONAL)
        |   +----(B)- Software Statement (OPTIONAL)
        v   v
    +-----------+                                      +---------------+
    |           |--(C)- Client Registration Request --+|    Client     |
    |           |+-(D)- Client Information Response ---| Registration  |
    |           |                                      +---------------+
    | Client or |--(E)- Read or Update Request -------+|               |
    | Developer |+-(F)- Client Information Response ---|    Client     |
    |           |--(G)- Delete Request ---------------+| Configuration |
    |           |+-(H)- Delete Confirmation -----------|               |
    +-----------+                                      +---------------+
</pre>
_OAuth 2.0 Dynamic Client Registration Protocol_

## Protocol flow

<pre>
     +--------+                               +---------------+
     |        |--(A)- Authorization Request -+|   Resource    |
     |        |+-(B)-- Authorization Grant ---|    Owner      |
     |        |                               +---------------+
     | Client |--(C)-- Authorization Grant --+| Authorization |
     |        |+-(D)----- Access Token -------|     Server    |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------+|    Resource   |
     |        |+-(F)--- Protected Resource ---|     Server    |
     +--------+                               +---------------+
</pre>
_Figure 1: Abstract Protocol Flow_

The abstract OAuth 2.0 flow illustrated in Figure 1 describes the
interaction between the four roles and includes the following steps:
 
**(A)**  The client requests authorization from the resource owner.  The
authorization request can be made directly to the resource owner
(as shown), or preferably indirectly via the authorization
server as an intermediary.  
**(B)**  The client receives an authorization grant, which is a
credential representing the resource owner's authorization,
expressed using one of four grant types defined in this
specification or using an extension grant type.  The
authorization grant type depends on the method used by the
client to request authorization and the types supported by the
authorization server.  
**(C)**  The client requests an access token by authenticating with the
authorization server and presenting the authorization grant.  
**(D)**  The authorization server authenticates the client and validates
the authorization grant, and if valid, issues an access token.  
**(E)**  The client requests the protected resource from the resource
server and authenticates by presenting the access token.  
**(F)**  The resource server validates the access token, and if valid,
serves the request.

## Machine to machine applications

With machine-to-machine (M2M) applications, such as CLIs, daemons, or services running on your back-end, the system authenticates and authorizes the app rather than a user. For this scenario, typical authentication schemes like username + password or social logins don't make sense. Instead, M2M apps use the Client Credentials Flow (defined in OAuth 2.0 RFC 6749, section 4.4).

The client can request an access token using only its client
credentials (or other supported means of authentication) when the
client is requesting access to the protected resources under its
control, or those of another resource owner that have been previously
arranged with the authorization server (the method of which is beyond
the scope of this specification).

<pre>
     +---------+                                  +---------------+
     | Client  |---(A)- Client Authentication ---+| Authorization |
     |         |+--(B)---- Access Token ----------|   Server      |
     +---------+                                  +---------------+
</pre>
_Figure 6: Client Credentials Flow_


## Roles 

The basic purpose of OAuth is to **grant the end user control over who may authenticate and authorize the end user.**
For this the OAuth protocol defines four roles:

| Role | Description | 
|---|---|
| **resource owner** | An entity (such as a human) capable of granting access to a protected resource. When the resource owner is a person, it is referred to as an end-user. In this context the _resource_ is the user identify. |
| **resource server**  | A server hosting the protected resources (i.e. a user account), that   can accept and respond to protected resource requests using access tokens.   This is a server that validates the _resource owner's resource_.   In practical terms this is a server that confirms a user identity. |
| **client**           | An application making protected resource requests on behalf of the resource owner and with its (the resource owner's) authorization.  The term "client" does not imply any particular implementation characteristics (e.g., whether the application executes on a server, a desktop, or other devices). This is typically an online service wishing to authenticate or authorize a user. |
| **authorization server**  | The server issuing access tokens to the client after successfully authenticating the resource owner and obtaining authorization (from the resource owner) that the client may authenticate the resource owner. |

What this means in a normal implementation is the end user (typically a human) is the _resource owner_, while the web server is the _client_. 

When a web server, configured to use OAuth, wishes a user to authentiate themselves the web server
instead requests an authorization server to authenticate (and in so doing to also authorize)
the end user. _However_, the user must first give permission for the web server (the client)
to authenticate (and authorize) the user.


## License

Copyright 2018 Key Bridge. Published under the Apache License, Version 2.0.
 
 
## References

 * [The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)

