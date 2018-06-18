# lib-oauth

**OAuth 2.0 Authorization Framework**

Provides a library of funtional primitives upon which a standards-compliant implementaiton of an OAuth 2.0 client and server can be developed. 

Implements [Request for Comments 6749](https://tools.ietf.org/html/rfc6749) in Java.

The OAuth 2.0 authorization framework enables a third-party
application to obtain limited access to an HTTP service, either on
behalf of a resource owner by orchestrating an approval interaction
between the resource owner and the HTTP service, or by allowing the
third-party application to obtain access on its own behalf.

## Roles 

OAuth defines four roles:

**resource owner**
An entity (such as a human) capable of granting access to a protected resource.
When the resource owner is a person, it is referred to as an
end-user. In this context the _resource_ is the user identify.

**resource server**
A server hosting the protected resources (i.e. a user account), that 
can accept and respond to protected resource requests using access tokens.
This is a server that validates the _resource owner's resource_.
In practical terms this is a server that confirms a user identity.

**client**
An application making protected resource requests on behalf of the
resource owner and with its (the resource owner's) authorization.  
The term "client" does not imply any particular implementation characteristics (e.g.,
whether the application executes on a server, a desktop, or other
devices). This is typically an online service wishing to authenticate
or authorize a user.

**authorization server**
The server issuing access tokens to the client after successfully
authenticating the resource owner and obtaining authorization (from the 
resource owner) that the client may authenticate the resource owner.

If that all sounds confusing it is until you understand the basic 
purpose of OAuth, which **grants the end user control over who may authenticate and authorize the end user.**

What this means in a normal implementation is the end user (typically a human) is the _resource owner_, while the web server is the _client_. 

When a web server, configured to use OAuth, wishes a user to authentiate themselves the web server
instead requests an authorization server to authenticate (and in so doing to also authorize)
the end user. _However_, the user must first give permission for the web server (the client)
to authenticate (and authorize) the user.

## Protocol flow

<pre>
     +--------+                               +---------------+
     |        |--(A)- Authorization Request -+|   Resource    |
     |        |                               |     Owner     |
     |        |+-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant --+| Authorization |
     | Client |                               |     Server    |
     |        |+-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------+|    Resource   |
     |        |                               |     Server    |
     |        |+-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+
</pre>
                     _Figure 1: Abstract Protocol Flow_

The abstract OAuth 2.0 flow illustrated in Figure 1 describes the
interaction between the four roles and includes the following steps:
 
(A)  The client requests authorization from the resource owner.  The
authorization request can be made directly to the resource owner
(as shown), or preferably indirectly via the authorization
server as an intermediary.

(B)  The client receives an authorization grant, which is a
credential representing the resource owner's authorization,
expressed using one of four grant types defined in this
specification or using an extension grant type.  The
authorization grant type depends on the method used by the
client to request authorization and the types supported by the
authorization server.

(C)  The client requests an access token by authenticating with the
authorization server and presenting the authorization grant.

(D)  The authorization server authenticates the client and validates
the authorization grant, and if valid, issues an access token.

(E)  The client requests the protected resource from the resource
server and authenticates by presenting the access token.

(F)  The resource server validates the access token, and if valid,
serves the request.


## License

Copyright 2018 Key Bridge. Published under the Apache License, Version 2.0.
 
 
## References

 * [The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)

