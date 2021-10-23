

package org.strongswan.android.security;

import android.net.http.SslCertificate;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TrustedCertificateEntry implements Comparable<TrustedCertificateEntry>
{
	private final X509Certificate mCert;
	private final String mAlias;
	private String mSubjectPrimary;
	private String mSubjectSecondary = "";
	private String mString;


	public TrustedCertificateEntry(String alias, X509Certificate cert)
	{
		mCert = cert;
		mAlias = alias;

		try
		{
			SslCertificate ssl = new SslCertificate(mCert);
			String o = ssl.getIssuedTo().getOName();
			String ou = ssl.getIssuedTo().getUName();
			String cn = ssl.getIssuedTo().getCName();
			if (!o.isEmpty())
			{
				mSubjectPrimary = o;
				if (!cn.isEmpty())
				{
					mSubjectSecondary = cn;
				}
				else if (!ou.isEmpty())
				{
					mSubjectSecondary = ou;
				}
			}
			else if (!cn.isEmpty())
			{
				mSubjectPrimary = cn;
			}
			else
			{
				mSubjectPrimary = ssl.getIssuedTo().getDName();
			}
		}
		catch (NullPointerException ex)
		{
				mSubjectPrimary = cert.getSubjectDN().getName();
		}
	}

		public String getSubjectPrimary()
	{
		return mSubjectPrimary;
	}

		public String getSubjectSecondary()
	{
		return mSubjectSecondary;
	}

	public List<String> getSubjectAltNames()
	{
		List<String> list = new ArrayList<>();
		try
		{
			Collection<List<?>> sans = mCert.getSubjectAlternativeNames();
			if (sans != null)
			{
				for (List<?> san : sans)
				{
					switch ((Integer)san.get(0))
					{
						case 1:
						case 2:
						case 7:
							list.add((String)san.get(1));
							break;
					}
				}
			}
			Collections.sort(list);
		}
		catch(CertificateParsingException ex)
		{
			ex.printStackTrace();
		}
		return list;
	}


	public String getAlias()
	{
		return mAlias;
	}


	public X509Certificate getCertificate()
	{
		return mCert;
	}

	@Override
	public String toString()
	{
		if (mString == null)
		{
			mString = mSubjectPrimary;
			if (!mSubjectSecondary.isEmpty())
			{
				mString += ", " + mSubjectSecondary;
			}
		}
		return mString;
	}

	@Override
	public int compareTo(TrustedCertificateEntry another)
	{
		int diff = mSubjectPrimary.compareToIgnoreCase(another.mSubjectPrimary);
		if (diff == 0)
		{
			diff = mSubjectSecondary.compareToIgnoreCase(another.mSubjectSecondary);
		}
		return diff;
	}
}
