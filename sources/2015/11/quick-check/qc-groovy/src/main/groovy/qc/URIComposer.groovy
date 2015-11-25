package qc

/**
 * Utils to build specific URIs
 */
class URIComposer {

    /**
     * Joins information about host, bucket and path to get the required
     * resource.
     *
     * Composer has been built to be 'smart', that means you can use
     * trailing slash with paths or not, the same applies to the
     * bucket name.
     *
     * e.g:
     *
     * compose('s3://username', '/bucket','/path')
     *
     * is the same as:
     *
     * compose('s3://username', 'bucket', 'path')
     *
     * @param host -> 's3://' + name of the username
     * @param bucket the name of the bucket
     * @param the path to get the resource
     */
    // tag::composer[]
    static URI compose(String host, String bucket, String path) {
        String treatedRoot = bucket.endsWith('/') ? bucket : "$bucket/"
        String treatedPath = path.dropWhile { it == '/' }

        return URI.create("$host$treatedRoot$treatedPath")
    }
    // end::composer[]


}
