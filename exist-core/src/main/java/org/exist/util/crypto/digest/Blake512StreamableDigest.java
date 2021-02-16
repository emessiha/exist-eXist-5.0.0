/*
 * eXist Open Source Native XML Database
 * Copyright (C) 2001-2018 The eXist Project
 * http://exist-db.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.exist.util.crypto.digest;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.Blake2bDigest;

import java.util.Arrays;

import static org.exist.util.crypto.digest.DigestType.BLAKE_512;

/**
 * Implementation of Blake2b 512 bit streamable digest.
 *
 * @author <a href="mailto:adam@evolvedbinary.com">Adam Retter</a>
 */
public class Blake512StreamableDigest implements StreamableDigest {
    private final ExtendedDigest ed = new Blake2bDigest(BLAKE_512.getDigestLength());

    @Override
    public void update(final byte b) {
        ed.update(b);
    }

    @Override
    public void update(final byte[] buf, final int offset, final int len) {
        ed.update(buf, offset, len);
    }

    @Override
    public DigestType getDigestType() {
        return BLAKE_512;
    }

    @Override
    public byte[] getMessageDigest() {
        final byte[] digestBytes = new byte[BLAKE_512.getDigestLengthBytes()];
        ed.doFinal(digestBytes, 0);
        return digestBytes;
    }

    @Override
    public MessageDigest copyMessageDigest() {
        return new MessageDigest(BLAKE_512,
                Arrays.copyOf(getMessageDigest(), BLAKE_512.getDigestLengthBytes())
        );
    }

    @Override
    public void reset() {
        ed.reset();
    }
}